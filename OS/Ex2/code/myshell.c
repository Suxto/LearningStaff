#include "myshell.h"
char elf_path[BUF_SIZE] = ":";//myshell可执行文件路径
char env[BUF_SIZE];
char path[BUF_SIZE];//当前工作路径
char cmd[BUF_SIZE];//当前命令

int main(int argc, char** argv) {
    int count = readlink("/proc/self/exe", elf_path + 1, BUF_SIZE - 1);//得到myshell的绝对路径
    elf_path[count - 6] = '\0';
    setenv("PATH", strcat(getenv("PATH"), elf_path), 1);//将文件目录加入环境变量
    // printf("export PATH=\"$PATH:%s\"", elf_path);
    // strcat(env, elf_path);
    // system(env);
    getcwd(path, BUF_SIZE);//得到当前工作路径

    if (argc == 1) {//没有参数，交互模式
        while (1) {
            printf("%s $ ", path);//展示当前路径
            fgets(cmd, BUF_SIZE, stdin);//读取输入
            if (cmd_analyzer(cmd) == 0)//分析命令行
                return 0;
        }
    }
    else {//有参数，执行批操作文件
        int fd = open(argv[1], O_RDONLY | O_CREAT, 0666);//重定向输入
        // close(fileno(stdin));
        close(STDIN_FILENO);
        // dup2(fd, fileno(stdin));
        dup2(fd, STDIN_FILENO);//将文件 重定向到stdin
        close(fd);
        while (1) {
            // printf("%s $ ", path);//展示当前路径
            if(fgets(cmd, BUF_SIZE, stdin)==NULL)break;//读取输入,遇到结尾退出
            if (cmd_analyzer(cmd) == 0)//分析命令行
                return 0;
        }
    }
    return 0;
}
