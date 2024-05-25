#include <IOSTREAM.H>
class Matrix {
   private:
    int **mat;
    int r, c;
    void init_mat(int row, int col) {
        mat = new int *[row];
        for (int i = 0; i < row; i++) {
            mat[i] = new int[col];
        }
    }

   public:
    int *operator[](int num) { return mat[num]; }
    Matrix(int row, int col, int **m) : r(row), c(col) {
        init_mat(row, col);
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) mat[i][j] = m[i][j];
    }
    Matrix(int row, int col, int num) : r(row), c(col) {
        init_mat(row, col);
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) mat[i][j] = num;
    }
    Matrix(Matrix &m) : r(m.r), c(m.c) {
        init_mat(m.r, m.c);
        for (int i = 0; i < m.r; i++)
            for (int j = 0; j < m.c; j++) mat[i][j] = m[i][j];
    }
    Matrix(int row, int col) : r(row), c(col) {
        init_mat(row, col);
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) mat[i][j] = 0;
    }
    Matrix operator+(Matrix &that) {
        if (r != that.r || c != that.c) {
            return *this;
        }
        Matrix tmp = Matrix(*this);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) tmp[i][j] += that[i][j];
        return tmp;
    }

    Matrix operator-(Matrix &that) {
        if (r != that.r || c != that.c) {
            return *this;
        }
        Matrix tmp = Matrix(*this);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) tmp[i][j] -= that[i][j];
        return tmp;
    }

    Matrix operator*(Matrix &that) {
        if (c != that.r) {
            return *this;
        }
        Matrix tmp = Matrix(r, that.c, 0);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < that.c; j++)
                for (int k = 0; k < c; k++) tmp[i][j] += mat[i][k] * that[k][j];
        return tmp;
    }
    friend istream &operator>>(istream &in, Matrix &m) {
        for (int i = 0; i < m.r; i++) {
            for (int j = 0; j < m.c; j++) {
                in >> m[i][j];
            }
        }
        return in;
    }
    friend ostream &operator<<(ostream &o, Matrix &m) {
        for (int i = 0; i < m.r; i++) {
            for (int j = 0; j < m.c; j++) {
                o << m[i][j] << ' ';
            }
            o << '\n';
        }
        return o;
    }
};

void main() {
    int a, b;
    cin >> a >> b;
    Matrix mat_a(a, b);
    cin >> mat_a;

    cin >> a >> b;
    Matrix mat_b(a, b);
    cin >> mat_b;
    cout << mat_a << '\n' << mat_b;
    cout << mat_a + mat_b << '\n' << mat_a - mat_b << '\n' << mat_a * mat_b;
}