library verilog;
use verilog.vl_types.all;
entity Calc_vlg_sample_tst is
    port(
        A               : in     vl_logic_vector(3 downto 0);
        B               : in     vl_logic_vector(3 downto 0);
        CN              : in     vl_logic;
        M               : in     vl_logic;
        S               : in     vl_logic_vector(3 downto 0);
        sampler_tx      : out    vl_logic
    );
end Calc_vlg_sample_tst;
