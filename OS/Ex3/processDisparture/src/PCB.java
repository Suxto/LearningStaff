class PCB {
    int pid; // 进程ID
    //String status;// 进程状态（0：新建，1：就绪，2：运行，3：阻塞，4：完成）
    ProcessState state;// 进程状态
    int priority; // 进程优先级
    int remainingTime; // 进程剩余运行时间
    int waitingTime; // 进程等待时间
    long startTime; // 进程开始时间
    long endTime; // 进程结束时间
    int blockTime; // 进程阻塞时间
    int cpuTime; // 进程使用CPU时间

    public PCB(int pid, int priority, int beginTime, int runTime) {
        this.pid = pid;
        this.state = ProcessState.NEW;//1;
        this.priority = priority;
        this.remainingTime = runTime;
        this.waitingTime = 0;
        this.startTime = beginTime;
        this.endTime = -1;
        this.blockTime = 0;
        this.cpuTime = 0;
    }


    public static void main(String[] args) {

    }
}