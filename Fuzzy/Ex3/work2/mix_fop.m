function [V,W]=mix_fop(R,S,T)
    V=syn(min(S,T),R);
    W=max(syn(T,S),syn(R,S));
end