onerror {exit -code 1}
vlib work
vlog -work work latch_8bit.vo
vlog -work work Waveform.vwf.vt
vsim -novopt -c -t 1ps -L cycloneive_ver -L altera_ver -L altera_mf_ver -L 220model_ver -L sgate work.latch_8bit_vlg_vec_tst -voptargs="+acc"
vcd file -direction latch_8bit.msim.vcd
vcd add -internal latch_8bit_vlg_vec_tst/*
vcd add -internal latch_8bit_vlg_vec_tst/i1/*
run -all
quit -f
