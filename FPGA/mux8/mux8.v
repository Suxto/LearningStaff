module mux8 (
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    select,
    andLogic,
    orLogic,
    notLogic,
    nandLogic,
    norLogic,
    nxorLogic,
    xorLogic,
    muxout
);
  input A, B, C, D, E, F, G, H;
  output andLogic, orLogic, notLogic, nandLogic, norLogic, nxorLogic, xorLogic;
  input [2:0] select;
  output muxout;

  mux81 mux (
      A,
      B,
      C,
      D,
      E,
      F,
      G,
      H,
      select,
      muxout
  );

  assign andLogic  = A && B;
  assign orLogic   = A || B;
  assign notLogic  = !A;
  assign nandLogic = !andLogic;
  assign norLogic  = !orLogic;
  assign xorLogic  = A ^ B;
  assign nxorLogic = !xorLogic;
endmodule


module mux81 (
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    sel,
    out
);
  input A, B, C, D, E, F, G, H;
  input [2:0] sel;
  output out;
  reg out;
  always @(A or B or C or D or E or F or G or H or sel)
    case (sel)
      3'b000:  out = A;
      3'b001:  out = B;
      3'b010:  out = C;
      3'b011:  out = D;
      3'b100:  out = E;
      3'b101:  out = F;
      3'b110:  out = G;
      3'b111:  out = H;
      default: out = 1'bx;
    endcase
endmodule
