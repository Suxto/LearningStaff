library verilog;
use verilog.vl_types.all;
entity Time_vlg_sample_tst is
    port(
        CLK             : in     vl_logic;
        nRst            : in     vl_logic;
        nSTART          : in     vl_logic;
        nSTOP           : in     vl_logic;
        sampler_tx      : out    vl_logic
    );
end Time_vlg_sample_tst;
