clc; close all; clear;
tic;
%calc
x = -2:1:4;
[r,c]=size(x);
A = 0.06 * x.^2 - 0.24 * x + 0.27;
B = -0.11 * x.^2 + 0.22 * x + 0.89;
AC = 1 - A;
BC = 1 - B;
AandB = min(A, B);
AorB = max(A, B);
AandAC = min(A, AC);
AorAC = max(A, AC);
BandBC = min(B, BC);
BorBC = max(B, BC);
AandBCC = 1 - AandB;
AorBCC = 1 - AorB;

%plot A
figure;
str=[repmat('  MS:',c,r) num2str(A')];
stem(x, A, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(A)]);
xlabel('x');
ylabel('Membership Function');
legend('A', 'location', 'best');
text(x,A,cellstr(str));
box off;

%plot B
figure;
str=[repmat('  MS:',c,r) num2str(B')];
stem(x, B, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(B)]);
xlabel('x');
ylabel('Membership Function');
legend('B', 'location', 'best');
text(x,B,cellstr(str));
box off;

%plot AC
figure;
str=[repmat('  MS:',c,r) num2str(AC')];
stem(x, AC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AC)]);
xlabel('x');
ylabel('Membership Function');
legend('A^C', 'location', 'best');
text(x,AC,cellstr(str));
box off;

%plot BC
figure;
str=[repmat('  MS:',c,r) num2str(BC')];
stem(x, BC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(BC)]);
xlabel('x');
ylabel('Membership Function');
legend('B^C', 'location', 'best');
text(x,BC,cellstr(str));
box off;

%plot AandB
figure;
str=[repmat('  MS:',c,r) num2str(AandB')];
stem(x, AandB, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AandB)]);
xlabel('x');
ylabel('Membership Function');
legend('A\capB', 'location', 'best');
text(x,AandB,cellstr(str));
box off;

%plot AorB
figure;
str=[repmat('  MS:',c,r) num2str(AorB')];
stem(x, AorB, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AorB)]);
xlabel('x');
ylabel('Membership Function');
legend('A\cupB', 'location', 'best');
text(x,AorB,cellstr(str));
box off;

%plot AandAC
figure;
str=[repmat('  MS:',c,r) num2str(AandAC')];
stem(x, AandAC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AandAC)]);
xlabel('x');
ylabel('Membership Function');
legend('A\capA^C', 'location', 'best');
text(x,AandAC,cellstr(str));
box off;

%plot AorAC
figure;
str=[repmat('  MS:',c,r) num2str(AorAC')];
stem(x, AorAC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AorAC)]);
xlabel('x');
ylabel('Membership Function');
legend('A\cupA^C', 'location', 'best');
text(x,AorAC,cellstr(str));
box off;

%plot BandBC
figure;
str=[repmat('  MS:',c,r) num2str(BandBC')];
stem(x, BandBC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(BandBC)]);
xlabel('x');
ylabel('Membership Function');
legend('B\capB^C', 'location', 'best');
text(x,BandBC,cellstr(str));
box off;

%plot BorBC
figure;
str=[repmat('  MS:',c,r) num2str(BorBC')];
stem(x, BorBC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(BorBC)]);
xlabel('x');
ylabel('Membership Function');
legend('B\cupB^C', 'location', 'best');
text(x,BorBC,cellstr(str));
box off;

%plot (AandB)C
figure;
str=[repmat('  MS:',c,r) num2str(AandBCC')];
stem(x, AandBCC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AandBCC)]);
xlabel('x');
ylabel('Membership Function');
legend('(A\capB)^C', 'location', 'best');
text(x,AandBCC,cellstr(str));
box off;

%plot (AorB)C
figure;
str=[repmat('  MS:',c,r) num2str(AorBCC')];
stem(x, AorBCC, '-b', 'LineWidth', 1);
axis([min(x), max(x), 0, 1.05 * max(AorBCC)]);
xlabel('x');
ylabel('Membership Function');
legend('(A\cupB)^C', 'location', 'best');
text(x,AorBCC,cellstr(str));
box off;
toc;
%K = input("");
