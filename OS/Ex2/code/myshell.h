#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <sys/types.h>
#include <sys/wait.h>
//常量
#define CMD_NUM 10 //含有的命令
#define BUF_SIZE 256//缓冲区大小
#define DIV_CHARS " \n\t\r"

//运行参数
//后台运行
#define BACKGROUND  1 
//重定向输入
#define  REIN 2 
//重定向截断输出
#define  REOUT_TRUNC  4 
//重定向增加输出
#define  REOUT_APPEND 8 

//工具
int cmd_analyzer(char*);//命令分析
int ex_cmd(int, char**);//命令执行
int not_buildin_cmd(int, char**, char*, char*); //外部命令

//内建命令
int myexit(char**);
int mycd(char**);
int myecho(char**);
int myclr(char**);
int mypause(char**);
int mydir(char**);
int myhelp(char**);
int myenviron(char**);
