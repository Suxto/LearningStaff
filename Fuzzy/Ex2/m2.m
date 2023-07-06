clc;close all;clear;
tic;
%calc A, B, AC and BC
x=-3:0.001:12;
A=exp(-((x-5).^2)/9);
B=exp(-((x-2).^2)/4);
AC=1-A;
BC=1-B;
AandB=min(A,B);
AorB=max(A,B);

%plot A and B
figure;
plot(x,A,'-b','LineWidth',1);
hold on;
plot(x,B,'--r','LineWidth',1);
axis([min(x),max(x),0,1.05*max(max(A),max(B))]);
xlabel('x');
ylabel('Membership Function');
legend('A','B','location','best');
box off;
%plot A and AC
figure;
plot(x,A,'-b','LineWidth',1);
hold on;
plot(x,AC,'--r','LineWidth',1);
axis([min(x),max(x),0,1.05*max(max(A),max(AC))]);
xlabel('x');
ylabel('Membership Function');
legend('A','A^C','location','best');
box off;
%plot B and BC
figure;
plot(x,B,'-b','LineWidth',1);
hold on;
plot(x,BC,'--r','LineWidth',1);
axis([min(x),max(x),0,1.05*max(max(B),max(BC))]);
xlabel('x');
ylabel('Membership Function');
legend('B','B^C','location','best');
box off;

%plot AandB and AorB
figure;
plot(x,AandB,'-b','LineWidth',1);
hold on;
plot(x,AorB,'--r','LineWidth',1);
axis([min(x),max(x),0,1.05*max(max(AandB),max(AorB))]);
xlabel('x');
ylabel('Membership Function');
legend('A\capB','A\cupB','location','best');
box off;
toc;
