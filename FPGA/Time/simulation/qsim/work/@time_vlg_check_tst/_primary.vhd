library verilog;
use verilog.vl_types.all;
entity Time_vlg_check_tst is
    port(
        T1              : in     vl_logic;
        T2              : in     vl_logic;
        T3              : in     vl_logic;
        sampler_rx      : in     vl_logic
    );
end Time_vlg_check_tst;
