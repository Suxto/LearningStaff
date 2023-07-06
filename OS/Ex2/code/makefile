.PHONY: clear

CC = gcc
RM = rm
EXE = myshell
OBJS = myshell.o utility.o

$(EXE): $(OBJS) 
	@$(CC) -o $(EXE) $(OBJS)
run: $(EXE)# clean
	@./$(EXE)
myshell.o: myshell.c myshell.h
	@$(CC) -o myshell.o -c myshell.c
utility.o: utility.c myshell.h
	@$(CC) -o utility.o -c utility.c
clear:
	@$(RM) $(EXE) $(OBJS)
clean:
	@$(RM) $(OBJS)
