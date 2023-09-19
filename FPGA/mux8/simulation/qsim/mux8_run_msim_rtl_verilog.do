transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vlog -vlog01compat -work work +incdir+C:/Users/suxto/Documents/FPGA/mux8 {C:/Users/suxto/Documents/FPGA/mux8/mux8.v}

