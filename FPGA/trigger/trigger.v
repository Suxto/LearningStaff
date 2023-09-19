module trigger (
    nCLR,
    CLK,
    D,
    Q
);
  input nCLR, CLK;
  input [7:0] D;
  output [7:0] Q;
  reg [7:0] Q;

  always @(posedge CLK or negedge nCLR) begin
    if (nCLR == 0) Q <= 8'b0;
    else Q <= D;
  end

endmodule
