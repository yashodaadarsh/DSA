class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        int l = strs.size();
        vector<pair<int,int>> freq(l);
        for( int i = 0 ; i < l ; i++ ){
            freq[i] = findCount( strs[i] );
        }

        vector<vector<vector<int>>> dp(l+1, vector<vector<int>>(m + 1, vector<int>(n + 1, 0)));

        for (int i = l - 1; i >= 0; i--) {
            int z = freq[i].first;
            int o = freq[i].second;
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int nopick = dp[i + 1][j][k];
                    int pick = 0;
                    if (z <= j && o <= k) {
                        pick = 1 + dp[i + 1][j - z][k - o];
                    }
                    dp[i][j][k] = max(pick, nopick);
                }
            }
        }

        return dp[0][m][n];

    }
private:
    pair<int,int> findCount( string str ){
        int o = 0 , z = 0;
        int n = str.size();
        for( int i = 0 ; i < n ; i++ ){
            if( str[i] == '1' ) o++;
            else z++;
        }
        return {z,o};
    }
};