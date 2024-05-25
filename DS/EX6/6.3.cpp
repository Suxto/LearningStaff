#include <bits/stdc++.h>

using namespace std;
const int SIZ = 100000;
int *extra = new int[SIZ + 1];
int *cpy = new int[SIZ + 1], *org = new int[SIZ + 1];
using i64 = unsigned long long;
// int cnt;

void getArr() {
	uniform_int_distribution<int> rd(1, SIZ);
	default_random_engine e{random_device{}()};
	for (int i = 0; i < SIZ; i++)
		org[i] = rd(e);
}

i64 insertSort(int *arr) {
	i64 cnt = 0;
	for (int i = 1; i < SIZ; i++) {
		int p = arr[i], j;
		for (j = i - 1; j >= 0; j--) {
			if (arr[j] <= p)
				break;
			arr[j + 1] = arr[j];
			cnt++;
		}
		arr[j + 1] = p;
		cnt++;
	}
	return cnt;
}

i64 shellSort(int *arr) {
	i64 cnt = 0;
	for (int len = SIZ >> 1; len > 0; len >>= 1) {
		for (int i = len; i < SIZ; i++) {
			int p = arr[i], j;
			for (j = i - len; j >= 0; j -= len) {
				if (arr[j] <= p)
					break;
				arr[j + len] = arr[j];
				cnt++;
			}
			arr[j + len] = p;
			cnt++;
		}
	}
	return cnt;
}

i64 bubbleSort(int *arr) {
	i64 cnt = 0;
	for (int i = 0; i < SIZ; i++) {
		for (int j = 0; j < SIZ - 1 - i; j++) {
			if (arr[j] > arr[j + 1]) {
				int t = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = t;
				cnt++;
			}
		}
	}
	return cnt;
}

i64 quickSortGo(int *arr, int l = 0, int r = SIZ) {
	static i64 cnt = 0;
	if (r <= l)
		return 0;
	int ll = l, rr = r;
	while (ll < rr) {
		while (ll < r && arr[++ll] < arr[l])
			;
		while (rr > l && arr[--rr] > arr[l])
			;
		if (ll < rr) {
			int t = arr[ll];
			arr[ll] = arr[rr];
			arr[rr] = t;
			cnt++;
		} else
			break;
	}
	swap(arr[l], arr[rr]);
	cnt++;
	quickSortGo(arr, l, rr);
	quickSortGo(arr, rr + 1, r);
	return cnt;
}

i64 quickSort(int *arr) {
	return quickSortGo(arr);
}

i64 selectionSort(int *arr) {
	i64 cnt = 0;
	for (int i = 0; i < SIZ - 1; i++) {
		int minPos = i + 1;
		for (int j = minPos; j < SIZ; j++) {
			if (arr[minPos] > arr[j])
				minPos = j;
		}
		swap(arr[i], arr[minPos]);
		cnt++;
	}
	return cnt;
}

i64 heapSort(int *arr) {
	i64 cnt = 0, size = SIZ;
	//    int *extra = new int[SIZ + 1];
	for (int i = 1; i <= SIZ; i++)
		extra[i] = arr[i - 1];
	function<void(int)> down = [&](int u) {
		int t = u;
		if ((u << 1) <= size && extra[u << 1] < extra[t])
			t = u << 1;
		if (((u << 1) + 1) <= size && extra[(u << 1) + 1] < extra[t])
			t = (u << 1) + 1;
		if (u != t) {
			swap(extra[u], extra[t]);
			cnt++;
			down(t);
		}
	};
	for (int i = SIZ >> 1; i > 0; i--)
		down(i);
	for (int i = 0; i < SIZ; i++) {
		arr[i] = extra[1];
		extra[1] = extra[size--];
		down(1);
	}
	//    delete[] extra;
	return cnt;
}

i64 mergeSort(int *arr) {
	//    int *extra = new int[SIZ];
	int len = 1;
	i64 cnt = 0;
	auto mergeGo = [&](const int *list, int *sorted) {
		int pos = 0;
		for (int i = 0; i < SIZ; i += len << 1) {
			int p1 = i, p2 = i + len;
			int lim1 = i + len > SIZ ? SIZ : i + len;
			int lim2 = i + (len << 1) > SIZ ? SIZ : i + (len << 1);
			while (p1 < lim1 && p2 < lim2) {
				if (list[p1] < list[p2] || p2 >= lim2)
					sorted[pos++] = list[p1++];
				else
					sorted[pos++] = list[p2++];
				cnt++;
			}
			while (p1 < lim1)
				sorted[pos++] = list[p1++], cnt++;
			while (p2 < lim2)
				sorted[pos++] = list[p2++], cnt++;
		}
	};
	while (len < SIZ) {
		mergeGo(arr, extra);
		len <<= 1;
		mergeGo(extra, arr);
		len <<= 1;
	}
	//    delete[] extra;
	return cnt;
}

void printArr(int *arr) {
	for (int i = 0; i < SIZ; i++)
		cout << arr[i] << ' ';
	cout << endl;
}

void arrCpy(const int *src, int *des) {
	for (int i = 0; i < SIZ; i++)
		des[i] = src[i];
}

void tester(const function<i64(int *)> &f) {
	i64 cnt;
	time_t beg, end;
	arrCpy(org, cpy);
	beg = std::clock();
	cnt = f(cpy);
	end = std::clock();
	cout << cnt << " times ";
	cout << "and takes " << end - beg << " ms\n\n";
}

int main() {
	getArr();

	cout << "Insert Sort swaps ";
	tester(insertSort);

	cout << "Shell Sort swaps ";
	tester(shellSort);

	cout << "Bubble Sort swaps ";
	tester(bubbleSort);

	cout << "Quick Sort swaps ";
	tester(quickSort);

	cout << "Selection Sort swaps ";
	tester(selectionSort);

	cout << "Heap Sort swaps ";
	tester(heapSort);

	cout << "Merge Sort swaps ";
	tester(mergeSort);
}
