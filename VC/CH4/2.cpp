#include <IOSTREAM.H>

class Person {
   public:
    char *name;
    int num;
    Person(char *n, int nu) : name(n), num(nu) {}
};

class Student : private Person {
   private:
    int num_class;
    int scores[3];

   public:
    Student(int num, char *name, int num_c, int score1, int score2, int score3)
        : Person(name, num), num_class(num_c) {
        scores[0] = score1;
        scores[1] = score2;
        scores[2] = score3;
    }
    friend ostream &operator<<(ostream &o, Student &s) {
        o << "Student " << s.name << " with number " << s.num << " in class "
          << s.num_class << '\n';
        o << "has score for 3 subject " << s.scores[0] << ", " << s.scores[1]
          << ", " << s.scores[2] << '\n';
        return o;
    }
};

class Teacher : private Person {
   private:
    char *department;
    char *job_title;

   public:
    Teacher(int num, char *name, char *dep, char *title)
        : Person(name, num), department(dep), job_title(title){};
    friend ostream &operator<<(ostream &o, Teacher &t) {
        o << "Teacher " << t.name << " with number " << t.num
          << " in department " << t.department << " has job title of "
          << t.job_title << '\n';
        return o;
    }
    // friend istream& operator>>(istream &i)
};

void main() {
    cout << "Please input Student`s name, num, class number\n";
    char name[20];
    int num, cn;
    cin >> name >> num >> cn;
    cout << "Please input 3 scores of student " << name << '\n';
    int a, b, c;
    cin >> a >> b >> c;
    Student s(num, name, cn, a, b, c);
    cout << endl;
    char name1[20];
    char dep[20], jt[20];
    cout << "Please input Teacher`s name, num, department and job title\n";
    cin >> name1 >> num >> dep >> jt;
    Teacher t(num, name1, dep, jt);
    cout << s << t;
}