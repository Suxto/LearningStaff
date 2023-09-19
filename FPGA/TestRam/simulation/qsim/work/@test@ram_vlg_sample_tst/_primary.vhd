library verilog;
use verilog.vl_types.all;
entity TestRam_vlg_sample_tst is
    port(
        ABus            : in     vl_logic_vector(7 downto 0);
        bclk            : in     vl_logic;
        bnRD            : in     vl_logic;
        bnWR            : in     vl_logic;
        in244           : in     vl_logic_vector(7 downto 0);
        sampler_tx      : out    vl_logic
    );
end TestRam_vlg_sample_tst;
