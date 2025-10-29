/**
 * Copyright (c) 2013, rxi
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the MIT license. See LICENSE for details.
 */

#ifndef DMT2_H_
#define DMT2_H_

#include <stdio.h>


void   *_dmt_alloc(size_t, int );
void   *_dmt_realloc(void*, size_t );
void    _dmt_free(void* );

size_t _dmt_size(void *ptr); 

void    dmt_dump(FILE*);
size_t  dmt_usage(void);
int     dmt_has(void *ptr);

void * __wrap_malloc( size_t x );    
void *__real_malloc  (size_t); 

void *  __wrap_calloc( int num, size_t sz) ;
void *__real_calloc  (int, size_t);

void * __wrap_realloc(void * ptr, size_t sz);
void *__real_realloc  (void *, size_t);


void  __wrap_free(void * ptr);
void *__real_free  (void *);

#define dmt_size(ptr)         _dmt_size(ptr)

#endif
