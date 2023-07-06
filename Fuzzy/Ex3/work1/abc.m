function [A,B]=abc(X,Y,Z)
    XC=1-X;
    ZC=1-Z;
    A=min(XC,syn(Z,Y));
    B=min(ZC,syn(Y,X));
end
