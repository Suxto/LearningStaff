library verilog;
use verilog.vl_types.all;
entity register_led_vlg_check_tst is
    port(
        clk_out         : in     vl_logic;
        outData         : in     vl_logic_vector(7 downto 0);
        sampler_rx      : in     vl_logic
    );
end register_led_vlg_check_tst;
