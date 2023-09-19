library verilog;
use verilog.vl_types.all;
entity latch_8bit_vlg_sample_tst is
    port(
        LE              : in     vl_logic;
        inData          : in     vl_logic_vector(7 downto 0);
        nOE             : in     vl_logic;
        sampler_tx      : out    vl_logic
    );
end latch_8bit_vlg_sample_tst;
