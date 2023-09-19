library verilog;
use verilog.vl_types.all;
entity register_led_vlg_sample_tst is
    port(
        clk             : in     vl_logic;
        inData          : in     vl_logic_vector(7 downto 0);
        nload           : in     vl_logic;
        sampler_tx      : out    vl_logic
    );
end register_led_vlg_sample_tst;
