void sort( int*a, int n )
{
	int ii, j, t;
	if ( n <2 ) 
	return;
	for( ii=0 ; ii < n-1; ii++) 
	{
		for( j=ii+ 1 ; j < n ; j++)
		{
			if ( a[ii] > a[ j]  )
			{
				t = a[ ii] ;
				a[ ii] =a[ j] ;
				a[ j]  = t;
			}
		}
	}
}
