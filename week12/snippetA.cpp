#include <iostream>

// 'a' is pass-by-value, 'b' is pass-by-reference
void f(int a, int &b) { 
    a = 2; 
    b = 3; 
}

int main() {
    int x = 1, y = 1;
    
    // Function call moved inside main()
    f(x, y);
    
    // Replaced pseudo-code 'print' with valid C++ output
    std::cout << "x = " << x << ", y = " << y << std::endl; 
    
    return 0;
}