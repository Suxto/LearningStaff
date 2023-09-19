module latch_8bit (
    inData,
    outData,
    LE,
    nOE
);
  input [7:0] inData;
  input LE, nOE;
  output [7:0] outData;
  reg [7:0] data;

  //   always @(nOE) begin
  //     if (nOE == 0) outData <= data;
  //     else outData <= 8'bzzzzzzzz;
  //   end
  assign outData = (nOE == 0 ? data : 8'bzzzzzzzz);

  always @(LE) begin
    if (LE == 1) begin
      data <= inData;
    end
  end
endmodule
