#include "myshell.h"
extern char path[BUF_SIZE];//当前工作路径
extern char elf_path[BUF_SIZE];//myshell可执行文件路径
// FILE* myin;
// FILE* myout;


char* cmd_list[] = {//命令列表
    "exit",
    "quit",
    "cd",
    "pwd",
    "echo",
    "clr",
    "pause",
    "dir",
    "help",
    "environ"
};
int (*func_list[]) (char**) = {//命令函数列表
    &myexit,
    &myexit,
    &mycd,
    &mycd,
    &myecho,
    &myclr,
    &mypause,
    &mydir,
    &myhelp,
    &myenviron
};

int cmd_analyzer(char* cmd) {//指令分析
    char** divs = (char**)malloc(BUF_SIZE * sizeof(char*));//建立指针数组，存入多个字符串
    char* word = strtok(cmd, DIV_CHARS);//得到第一个字符
    char* in, * out;//重定向文件路径e
    in = out = NULL;
    // myin = stdin;//如果没有重定向默认为stdin
    // myout = stdout;//如果没有重定向默认为stdout
    int pos = 0;
    int singal = 0;
    int fd = STDOUT_FILENO;
    int fd_old = fd;
    while (word != NULL) {
        if (strcmp(word, "&") == 0) {//判断是否需要后台运行
            singal |= BACKGROUND;
            break;
        }
        // else if (strcmp(word, "<") == 0) {//判断是否需要重定向输入
        //     char* pre = word;
        //     word = strtok(NULL, DIV_CHARS);
        //     if (word == NULL) {
        //         divs[pos++] = pre;
        //         break;
        //     }
        //     else {
        //         int fd_in = open(word, O_RDONLY | O_CREAT, 0666);
        //         int fd_in_old = dup(STDIN_FILENO);
        //         dup2(fd_in, STDIN_FILENO);
        //         divs[pos] = (char*)malloc(BUF_SIZE * sizeof(char));
        //         while (scanf("%s", divs[pos]) != EOF) {
        //             pos++;
        //             divs[pos] = (char*)malloc(BUF_SIZE * sizeof(char));
        //         }
        //         free(divs[pos]);
        //         dup2(fd_in_old, STDIN_FILENO);
        //         close(fd_in);
        //         divs[pos] = NULL;
        //         word = strtok(NULL, DIV_CHARS);
        //         continue;
        //     }
        // }
        divs[pos++] = word;
        word = strtok(NULL, DIV_CHARS);
    }
    divs[pos] = NULL;
    for (int i = 1;i < pos;i++) {//检查是否需要重定向
        if (strcmp(divs[i], "<") == 0 && divs[i + 1] != NULL) {//重定向输入
            singal |= REIN;
            in = divs[i + 1];
        }
        else if (strcmp(divs[i], ">") == 0 && divs[i + 1] != NULL) {//重定向截断输出
            singal |= REOUT_TRUNC;
            out = divs[i + 1];
            // fd = open(divs[i + 1], O_WRONLY | O_CREAT | O_TRUNC, 0666);
            // fd_old = dup(STDOUT_FILENO);
            // dup2(fd, STDOUT_FILENO);
        }
        else if (strcmp(divs[i], ">>") == 0 && divs[i + 1] != NULL) {//重定向增加输出
            singal |= REOUT_APPEND;
            out = divs[i + 1];
            // fd = open(divs[i + 1], O_WRONLY | O_CREAT | O_APPEND, 0666);
            // fd_old = dup(STDOUT_FILENO);
            // dup2(fd, STDOUT_FILENO);
        }
        else continue;
        pos = i < pos ? i : pos;//重定向的内容不用传参
        i++;
    }
    divs[pos] = NULL;//标记参数列表结尾
    if (divs[0] == NULL) {//输入为空
        free(divs);
        return 1;
    }
    for (int i = 0;i < CMD_NUM;i++) {//分析是第几个内置函数
        if (!strcmp(divs[0], cmd_list[i])) {
            int status;//保存执行结果
            if (singal & REOUT_TRUNC) {//重定向截断输出
                fd = open(out, O_WRONLY | O_TRUNC | O_CREAT, 0666);
                fd_old = dup(STDOUT_FILENO);
                close(fileno(stdout));
                dup2(fd, fileno(stdout));
                close(fd);
            }
            else if (singal & REOUT_APPEND) {//重定向增加输出
                fd = open(out, O_WRONLY | O_APPEND | O_CREAT, 0666);
                fd_old = dup(STDOUT_FILENO);
                close(fileno(stdout));
                dup2(fd, fileno(stdout));
                close(fd);
            }
            status = ex_cmd(i, divs);
            free(divs);
            if (fd_old != STDOUT_FILENO) {//如果重定向，输出恢复到stdio
                dup2(fd_old, STDOUT_FILENO);
                close(fd);
            }
            return status;
        }
    }
    not_buildin_cmd(singal, divs, in, out);
    free(divs);//释放内存
    return 1;
}
int ex_cmd(int n, char** args) {//前台执行命令
    return (*func_list[n])(args);
}

int not_buildin_cmd(int singal, char** args, char* in, char* out) {//外部命令
    pid_t pid = fork();
    int statue = 1;
    if (pid == 0) {//子进程
        if (singal & REOUT_TRUNC) {//重定向截断输出
            int fd = open(out, O_WRONLY | O_TRUNC | O_CREAT, 0666);
            // close(fileno(stdout));
            close(STDOUT_FILENO);
            // dup2(fd, fileno(stdout));
            dup2(fd, STDOUT_FILENO);
            close(fd);
        }
        else if (singal & REOUT_APPEND) {//重定向增加输出
            int fd = open(out, O_WRONLY | O_APPEND | O_CREAT, 0666);
            // close(fileno(stdout));
            close(STDOUT_FILENO);
            // dup2(fd, fileno(stdout));
            dup2(fd, STDOUT_FILENO);
            close(fd);
        }
        if (singal & REIN) {//重定向输入
            int fd = open(in, O_RDONLY | O_CREAT, 0666);
            // close(fileno(stdin));
            close(STDIN_FILENO);
            // dup2(fd, fileno(stdin));
            dup2(fd, STDIN_FILENO);
            close(fd);
        }
        execvp(args[0], args);
        // char** new_env = { __environ };
        // execve(args[0], args, __environ);
        // execv(args[0], args);
        perror("execvp error");
        exit(EXIT_FAILURE);
    }
    else if (pid < 0) {
        perror("fork error");
    }
    else {
        if (singal & BACKGROUND) return 1;//后台执行
        else waitpid(pid, &statue, 0);//前台执行，等待子进程完成
    }
    // return statue;
    return 1;
}

int mycd(char** args) {//cd命令
    if (args[1] == NULL) {//没有参数，就输出当前目录
        printf("current directory: %s\n", path);
    }
    else {//有参数选择目录
        if (chdir(args[1]) != 0) perror("cd error");//打开失败，显示错误信息
        else getcwd(path, BUF_SIZE);//打开成功，更新path
    }
    return 1;
}

int myexit(char** args) {//返回0表示退出
    return 0;
}

int myecho(char** args) {//echo命令
    int pos = 1;
    while (args[pos] != NULL) {//按序输出每个参数
        printf("%s ", args[pos++]);
    }
    printf("\n");
    return 1;
}

int myclr(char** args) {//清楚屏幕
    system("clear");
    return 1;
}

int mypause(char** args) {//暂停命令
    puts("Paused, press enter to continue");
    while (getchar() != '\n');
    return 1;
}


int mydir(char** args) {//dir命令
    return not_buildin_cmd(0, args, NULL, NULL);
}

int myhelp(char** args) {//help命令
    char* helps = "This is a shell developed by Suxton\n\
        You can use this shell as other shells\n\
        There are several build-in functions in this shell\n\
        cd <directory>: select a directory. It would show current directory if you invoke whout arguments\n\
        clr : clear the screen\n\
        dir <directory>: - List the contents of directory <directory>.\n\
        environ : List all the environment strings.\n\
        echo <comment> : Display <comment> on the display followed by a new line\n\
        help : Display this user manual using the more filter.\n\
        pause : Pause operation of the shell until 'Enter' is pressed.\n\
        quit : Quit the shell.\n\
        ues < to redirect stdin\n\
        use > to redirect stdout to a file with trunc\n\
        use >> to redirect stdout to a file with append\n";//我的终端没有中文字体，所以都是英文
    FILE* pipe = popen("more", "w");//用管道传给more
    fprintf(pipe, "%s", helps);
    pclose(pipe);
    return 1;
}

int check_char(char ch) {//排除变色控制字符
    return (isalpha(ch) || isalnum(ch) || ch == ':' || ch == '/' ||
        ch == '_' || ch == '=' || ch == '-' ||
        ch == '.' || ch == '*' || ch == ';' ||
        ch == '%' || ch == '\t' || ch == '\n' ||
        ch == ' ' || ch == '\r'); //return 1;
    // return 0;
}
int myenviron(char** args) {//environ命令
    int cnt = 0;
    while (__environ[cnt] != NULL) {
        // int bound = strlen()
        for (int i = 0;;i++) {
            if (check_char(__environ[cnt][i]))
                putchar(__environ[cnt][i]);
            else break;
        }
        cnt++;
        putchar('\n');
    }
    return 1;
}
