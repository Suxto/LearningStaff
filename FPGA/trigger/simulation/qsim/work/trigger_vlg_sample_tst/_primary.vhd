library verilog;
use verilog.vl_types.all;
entity trigger_vlg_sample_tst is
    port(
        CLK             : in     vl_logic;
        D               : in     vl_logic_vector(7 downto 0);
        nCLR            : in     vl_logic;
        sampler_tx      : out    vl_logic
    );
end trigger_vlg_sample_tst;
