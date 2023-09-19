library verilog;
use verilog.vl_types.all;
entity Time is
    port(
        nRst            : in     vl_logic;
        CLK             : in     vl_logic;
        nSTART          : in     vl_logic;
        nSTOP           : in     vl_logic;
        T1              : out    vl_logic;
        T2              : out    vl_logic;
        T3              : out    vl_logic
    );
end Time;
