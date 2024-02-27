use csv::ReaderBuilder;
use std::collections::{BTreeMap, BTreeSet};
use std::error::Error;

const MIN_SUPP: f64 = 0.2;
const MIN_CONF: f64 = 0.6;

fn read_csv(file_path: &str) -> Result<Vec<BTreeSet<String>>, Box<dyn Error>> {
    let mut rdr = ReaderBuilder::new().from_path(file_path)?;
    let mut data = Vec::new();
    for result in rdr.records() {
        let record = result?;
        let trans: BTreeSet<String> = record.iter()
            .filter_map(|s| if s.is_empty() { None } else { Some(s.to_string()) })
            .collect();
        data.push(trans);
    }
    Ok(data)
}

fn main() {
    let mut t: Vec<BTreeSet<String>> = read_csv("data/data2.csv").unwrap();
    // println!("{:?}", t);
    let mut candidates: Vec<BTreeMap<BTreeSet<String>, i32>> = vec![];
    get_c1(&mut t, &mut candidates);
    while candidates.last().unwrap().len() > 1 {// add new layer
        get_next_c(&mut t, &mut candidates);
    }
    if candidates.last().unwrap().len() == 0 {// remove empty layer
        candidates.pop();
    }
    gen_rules(&t, &candidates);
}

fn get_c1(t: &mut Vec<BTreeSet<String>>, candidates: &mut Vec<BTreeMap<BTreeSet<String>, i32>>) {
    let num: i32 = (t.len() as f64 * MIN_SUPP).ceil() as i32;
    let mut cnt: BTreeMap<BTreeSet<String>, i32> = BTreeMap::new();

    for ts in t.iter() {
        for str in ts.iter() {
            let mut tmp: BTreeSet<String> = BTreeSet::new();
            tmp.insert(str.clone());
            *cnt.entry(tmp).or_insert(0) += 1;
        }
    }
    candidates.push(
        cnt.iter()
            .filter(|(_, n)| **n >= num)
            .map(|(k, v)| (k.clone(), *v))
            .collect(),
    );
    println!("C_{} {:?}", candidates.len(), candidates.last().unwrap());
}

fn get_next_c(
    t: &Vec<BTreeSet<String>>,
    candidates: &mut Vec<BTreeMap<BTreeSet<String>, i32>>,
) {
    let num: i32 = (t.len() as f64 * MIN_SUPP).ceil() as i32;
    let pre = candidates.last().unwrap();

    let mut now: BTreeMap<BTreeSet<String>, i32> = BTreeMap::new();
    for (idx1, (strs1, _)) in pre.iter().enumerate() {
        for (idx2, (strs2, _)) in pre.iter().enumerate() {
            if idx1 >= idx2 {
                continue;
            }
            if strs1
                .iter()
                .rev()
                .skip(1)
                .zip(strs2.iter().rev().skip(1))
                .fold(true, |acc, (a, b)| acc & (*a == *b))// last one is different
            {
                let mut tmp: BTreeSet<String> = strs1.clone();// cur set
                tmp.insert(strs2.last().unwrap().clone());
                let val = count_supp(t, &tmp);
                if val >= num && is_valid(&mut tmp, pre)/* pruning */ {
                    now.insert(tmp, val);
                }
            }
        }
    }
    candidates.push(now);
    println!("C_{} {:?}", candidates.len(), candidates.last().unwrap());
}

fn count_supp(t: &Vec<BTreeSet<String>>, tmp: &BTreeSet<String>) -> i32 {
    t.iter().fold(0, |acc, strs| {
        if tmp.len()
            == tmp.iter()
            .fold(0, |ac, str| if strs.contains(str) { ac + 1 } else { ac })
        {
            acc + 1
        } else {
            acc
        }
    })
}

fn is_valid(tmp: &BTreeSet<String>, pre: &BTreeMap<BTreeSet<String>, i32>) -> bool {
    for (r, _) in pre.iter() {
        for i in 0..tmp.len() {
            let mut cnt = 0;
            for (idx, str) in tmp.iter().enumerate() {
                if idx == i {
                    continue;
                }
                if r.contains(str) {
                    cnt += 1;
                }
            }
            if cnt == r.len() {
                return true;
            }
        }
    }
    false
}

fn cnt_tmp(f: &Vec<BTreeMap<BTreeSet<String>, i32>>, tmp: &BTreeSet<String>) -> i32 {
    f[tmp.len() - 1].iter()
        .find(|(key, _)| key.eq(&tmp))
        .map(|(_, val)| *val)
        .unwrap_or(-1)
}

fn gen_rules(t: &Vec<BTreeSet<String>>, f: &Vec<BTreeMap<BTreeSet<String>, i32>>) {
    for r in f.iter() {
        for (strs, all_cnt) in r.iter() {
            if strs.len() < 2 { continue; }
            let mut h: Vec<BTreeMap<BTreeSet<String>, i32>> = vec![];
            let mut tmp = strs.clone();
            let mut now: BTreeMap<BTreeSet<String>, i32> = BTreeMap::new();
            for str in strs.iter() {
                tmp.remove(str);
                let front_cnt = cnt_tmp(f, &tmp);
                if front_cnt != -1 && *all_cnt as f64 / front_cnt as f64 >= MIN_CONF {
                    let mut st = BTreeSet::new();
                    st.insert(str.clone());
                    println!("{:?} => {:?} supp={:.3},conf={:.3}",
                             tmp, &st, *all_cnt as f64 / t.len() as f64, *all_cnt as f64 / front_cnt
                            as f64
                    );
                    now.insert(st, 0);
                }
                tmp.insert(str.clone());
            }
            h.push(now);
            ap_gen_rules(t, strs, all_cnt, &mut h, f);
        }
    }
}

fn ap_gen_rules(
    t: &Vec<BTreeSet<String>>,
    strs: &BTreeSet<String>,
    all_cnt: &i32,
    h: &mut Vec<BTreeMap<BTreeSet<String>, i32>>,
    f: &Vec<BTreeMap<BTreeSet<String>, i32>>,
) {
    if strs.len() <= h.last().unwrap().len() || h.last().unwrap().is_empty() {
        return;
    }
    get_next_c(t, h);
    let now = h.last_mut().unwrap();
    let mut to_remove = Vec::new();
    for (back, _) in now.iter() {
        let mut tmp = strs.clone();
        back.iter().for_each(|x| { tmp.remove(x); });
        if tmp.is_empty() { continue; }
        let front_cnt = cnt_tmp(f, &tmp);
        if front_cnt != -1 && *all_cnt as f64 / front_cnt as f64 >= MIN_CONF {
            // let st = vec![str.clone()].iter().collect();
            // now.insert(st, 0);
            println!("{:?} => {:?} supp={:.3},conf={:.3}",
                     tmp, back, *all_cnt as f64 / t.len() as f64, *all_cnt as f64 / front_cnt as f64
            );
        } else {
            to_remove.push(back.clone());
        }
    }
    for key in to_remove.iter() {
        if let Some(_) = now.remove(key) {};
    }
    ap_gen_rules(t, strs, all_cnt, h, f);
}

/*vec![
    vec!["student", "teach", "school"]
        .into_iter()
        .map(String::from)
        .collect(),
    vec!["student", "school", "mathematics"]
        .into_iter()
        .map(String::from)
        .collect(),
    vec!["teach", "school", "city", "game"]
        .into_iter()
        .map(String::from)
        .collect(),
    vec!["basketball", "football"]
        .into_iter()
        .map(String::from)
        .collect(),
    vec!["basketball", "sporter", "audience"]
        .into_iter()
        .map(String::from)
        .collect(),
    vec!["football", "trainer", "game", "team"]
        .into_iter()
        .map(String::from)
        .collect(),
    vec!["basketball", "team", "city", "game"]
        .into_iter()
        .map(String::from)
        .collect(),
];
*/
