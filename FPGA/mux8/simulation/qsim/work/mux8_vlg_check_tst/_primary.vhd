library verilog;
use verilog.vl_types.all;
entity mux8_vlg_check_tst is
    port(
        andLogic        : in     vl_logic;
        muxout          : in     vl_logic;
        nandLogic       : in     vl_logic;
        norLogic        : in     vl_logic;
        notLogic        : in     vl_logic;
        nxorLogic       : in     vl_logic;
        orLogic         : in     vl_logic;
        xorLogic        : in     vl_logic;
        sampler_rx      : in     vl_logic
    );
end mux8_vlg_check_tst;
