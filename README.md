# Introduction to the Problem
We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)
Researchers are interested in the following question: 
**if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates?** 

## Modules in the project
- Percolation.java
    creates n-by-n grid, with all sites initially blocked 
 - PercolationStats.java
  PercolationStats performs a monteCarlo Simulaton to estimate P*, when running PercolationStats.java provide n and t as an argument for
  the main method. n is the size of the grid and t is the number of trials.
 - algs4.jar includes an implementation to WeightedQuickFind

