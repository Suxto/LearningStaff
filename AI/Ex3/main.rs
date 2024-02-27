use std::collections::BTreeSet;
use std::{
    fs::File,
    io::{BufRead, BufReader},
};

type DataRow = Vec<AttrVal>;

static THRESHOLD: f64 = 0.0;

enum NodeType {
    Node(Vec<Node>),
    Leaf(Vec<DataRow>),
}

struct Node {
    name: String,
    node_type: NodeType,
}

fn entropy(attributes: &Vec<Attribute>, data: &Vec<DataRow>) -> f64 {
    let mut e = 0f64;
    let data_num = data.len();
    match &attributes.last().unwrap().values {
        AttrType::Strings(attrs) => {
            let type_num = attrs.len();
            let mut cnt: Vec<i32> = vec![0; type_num];
            for row in data.iter() {
                if let AttrVal::String(row_class) = row.last().unwrap() {
                    for (idx, now) in attrs.iter().enumerate() {
                        if now.eq(row_class) {
                            cnt[idx] += 1;
                        }
                        break;
                    }
                }
            }
            for i in cnt.iter() {
                if *i == 0 {
                    continue;
                }
                let pr = *i as f64 / data_num as f64;
                let lg = pr.log2();
                // e -= now * now.log2();
                e -= pr * lg;
            }
        }
        _ => {
            todo!("add continue value support");
        }
    };
    e
}

fn entropy_attr(attr_num: &usize, attributes: &Vec<Attribute>, data: &Vec<DataRow>) -> f64 {
    let mut e = 0f64;
    let data_num = data.len();
    match &attributes[*attr_num].values {
        AttrType::Strings(attrs) => {
            for attr_val in attrs.iter() {
                let mut div: Vec<DataRow> = vec![];
                for row in data.iter() {
                    if let AttrVal::String(now) = &row[*attr_num] {
                        if attr_val.eq(now) {
                            div.push(row.clone());
                        }
                    }
                }
                if div.is_empty() {
                    continue;
                }
                e += (div.len() as f64 / data_num as f64) * entropy(attributes, &div);
            }
        }
        _ => todo!("add continue value support"),
    };
    e
}

fn decision_tree(
    data: Vec<DataRow>,
    attributes: &Vec<Attribute>,
    mut mask: BTreeSet<usize>,
    node: &mut Node,
) {
    let p_0 = entropy(attributes, &data);
    if mask.is_empty() || p_0 < 1e-12 {
        if let NodeType::Leaf(arr) = &mut node.node_type {
            *arr = data;
        }
        return;
    }
    let mut entropy_arr: Vec<(f64, usize)> = vec![];
    for attr_num in mask.iter() {
        entropy_arr.push((entropy_attr(attr_num, attributes, &data), *attr_num));
    }
    // println!("{:?}",entropy_arr);
    entropy_arr.sort_by(|(a, _), (b, _)| a.partial_cmp(b).unwrap());
    let (p_g, best_attr) = entropy_arr.first().unwrap().clone();
    if p_0 - p_g < THRESHOLD {
        if let NodeType::Leaf(arr) = &mut node.node_type {
            *arr = data;
        }
        return;
    }
    mask.remove(&best_attr);
    let attr_name = &attributes[best_attr].name;
    node.node_type = NodeType::Node(vec![]);

    match &attributes[best_attr].values {
        AttrType::Strings(attrs) => {
            for attr_val in attrs {
                let mut distro_data: Vec<DataRow> = vec![];
                for row in data.iter() {
                    if let AttrVal::String(row_val) = &row[best_attr] {
                        if attr_val.eq(row_val) {
                            distro_data.push(row.clone());
                        }
                    }
                }
                if !distro_data.is_empty() {
                    let mut new_node = Node {
                        name: format!("{attr_name}=={attr_val}").to_string(),
                        node_type: NodeType::Leaf(vec![]),
                    };
                    if let NodeType::Node(sons) = &mut node.node_type {
                        decision_tree(distro_data, attributes, mask.clone(), &mut new_node);
                        sons.push(new_node);
                    }
                }
            }
        }
        _ => {}
    }
}

fn show_tree(node: &Node, layer: usize) {
    let spaces: String = std::iter::repeat('\t').take(layer).collect();
    match &node.node_type {
        NodeType::Leaf(data) => {
            println!("{spaces}Leaf: {}", node.name);
            println!("{spaces}Data contains:");
            for row in data.iter() {
                println!("{spaces}{:?}", row);
            }
        }
        NodeType::Node(sons) => {
            println!("{spaces}Node: {}", node.name);
            for son in sons {
                show_tree(son, layer + 1);
            }
        }
    }
}

fn main() {
    let (attributes, data) = read_data("data.arff");
    let mask = show_info(&attributes);
    let mut tree: Node = Node {
        name: String::from("All"),
        node_type: NodeType::Leaf(vec![]),
    };
    decision_tree(data, &attributes, mask, &mut tree);
    show_tree(&tree, 0);
}

fn show_info(attributes: &Vec<Attribute>) -> BTreeSet<usize> {
    println!("There are {} attributes:", attributes.len());
    for (idx, attr) in attributes.iter().enumerate() {
        println!("Attr{} :{}", idx, attr.to_string());
    }
    println!("Please input the attribute index you want to remove, split by space(can be empty)");
    let mut buff = String::new();
    let _ = std::io::stdin().read_line(&mut buff);
    let mut mask: BTreeSet<usize> = (0..attributes.len() - 1).collect();
    if !buff.is_empty() {
        buff.split_whitespace().into_iter().for_each(|x| {
            mask.remove(&(x.parse().unwrap()));
        })
    }
    mask
}

fn read_data(path: &str) -> (Vec<Attribute>, Vec<DataRow>) {
    let mut attributes: Vec<Attribute> = vec![];
    let mut data: Vec<DataRow> = vec![];

    let file = File::open(path).unwrap();
    let reader = BufReader::new(file);
    let mut reading_data = false;
    for (idx, line) in reader.lines().enumerate() {
        let l = line.unwrap();
        if reading_data {
            let vals = l.split(',');
            let mut row: DataRow = vec![];
            row.reserve(attributes.len());
            for (idx, str) in vals.enumerate() {
                match attributes[idx].values {
                    AttrType::Numbers => row.push(AttrVal::Number(str.parse().unwrap_or(0))),
                    AttrType::Strings(_) => row.push(AttrVal::String(str.to_owned())),
                }
            }
            if row.len() != attributes.len() {
                panic!("Data error at line {}", idx);
            }
            data.push(row);
        } else {
            let strs: Vec<&str> = l.split(' ').collect();
            if strs[0] == "@attribute" {
                attributes.push(Attribute {
                    name: strs[1].to_owned(),
                    values: if strs[2] == "numeric" {
                        AttrType::Numbers
                    } else {
                        AttrType::Strings(
                            strs[2][1..strs[2].len() - 1]
                                .split(',')
                                .map(|x| x.to_owned())
                                .collect(),
                        )
                    },
                })
            } else if strs[0] == "@data" {
                reading_data = true;
            }
        }
    }
    return (attributes, data);
}

#[derive(Debug)]
enum AttrVal {
    Number(i32),
    String(String),
}

impl Clone for AttrVal {
    fn clone(&self) -> Self {
        match self {
            AttrVal::Number(x) => AttrVal::Number(x.clone()),
            AttrVal::String(str) => AttrVal::String(str.clone()),
        }
    }
}

#[derive(Debug)]
enum AttrType {
    Numbers,
    Strings(Vec<String>),
}

#[derive(Debug)]
struct Attribute {
    name: String,
    values: AttrType,
}

impl ToString for Attribute {
    fn to_string(&self) -> String {
        let vals = match &self.values {
            AttrType::Numbers => "number".to_string(),
            AttrType::Strings(strs) => format!("{{{}}}", strs.join(",")),
        };
        format!("Name: {}, Vals: {}", self.name, vals)
    }
}
