library verilog;
use verilog.vl_types.all;
entity mux4_vlg_sample_tst is
    port(
        in0             : in     vl_logic;
        in1             : in     vl_logic;
        in2             : in     vl_logic;
        in3             : in     vl_logic;
        sel             : in     vl_logic_vector(1 downto 0);
        sampler_tx      : out    vl_logic
    );
end mux4_vlg_sample_tst;
