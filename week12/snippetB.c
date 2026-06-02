#include <iostream>

// 'p' is pass-by-pointer, 'q' is pass-by-value
void g(int *p, int q) { 
    *p = 10; 
    q = 20; 
}

int main() {
    int m = 5, n = 5;
    
    // Function call moved inside main()
    g(&m, n);
    
    // Output the results
    std::cout << "m = " << m << ", n = " << n << std::endl;
    
    return 0;
}