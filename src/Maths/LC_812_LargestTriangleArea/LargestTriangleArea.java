package Maths.LC_812_LargestTriangleArea;

class LargestTriangleArea {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0.0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double x1 = points[i][0], y1 = points[i][1];
                    double x2 = points[j][0], y2 = points[j][1];
                    double x3 = points[k][0], y3 = points[k][1];

                    // Side lengths using distance formula
                    double a = Math.hypot(x2 - x1, y2 - y1);
                    double b = Math.hypot(x3 - x2, y3 - y2);
                    double c = Math.hypot(x1 - x3, y1 - y3);

                    // Semi-perimeter
                    double s = (a + b + c) * 0.5;

                    // Heron's formula (check valid triangle)
                    double area = Math.sqrt(Math.max(0,
                        s * (s - a) * (s - b) * (s - c)
                    ));

                    // Shoelace formula
                    double Sarea = 0.5 * Math.abs(
                            x1 * (y2 - y3) +
                                    x2 * (y3 - y1) +
                                    x3 * (y1 - y2)
                    );

                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
