#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int x;
    cin >> x;

    set<int> lights;
    lights.insert(0);
    lights.insert(x);

    map<int, int> segMap;
    segMap[x] = 1;

    int t;
    cin >> t;

    while (t--) {
        int p;
        cin >> p;

        auto it = lights.upper_bound(p);
        int right = *it;
        int left = *prev(it);
        int prevSeg = right - left;

        if (--segMap[prevSeg] == 0)
            segMap.erase(prevSeg);

        int newLeft = p - left;
        int newRight = right - p;
        segMap[newLeft]++;
        segMap[newRight]++;

        lights.insert(p);

        cout << segMap.rbegin()->first;
        if (t) cout << ' ';
    }
    cout << '\n';
}