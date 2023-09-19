module TestRam (
    bclk,
    in244,
    ABus,
    DBus,  //io
    bnRD,  //low
    bnWR
);
    input bclk, bnRD, bnWR;
    input [7:0] ABus;
    output [7:0] DBus;
    input [7:0] in244;
    wire [7:0] q;
    reg  [7:0] DBus;
    always @(*) begin
        if (~bnRD && bnRD == bnWR) DBus <= 8'bzzzzzzzz;
        else if (~bnRD) DBus <= q;
        else if (~bnWR) DBus <= in244;
    end

    RAM R (
        .address(ABus),
        .clock  (bclk),
        .data   (DBus),
        .rden   (~bnRD),
        .wren   (~bnWR),
        .q      (q)
    );
endmodule
