#include <stdlib.h>

void standard_malloc() {
    void *ptr = malloc(32);
    free(ptr);
}
