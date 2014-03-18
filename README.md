# AI-algorithms
A selection of well-known AI and machine learning algorithms, implemented in Java. The code is very domain-agnostic, so it can be adapted for many tasks. The goal is to create a lightweight library for quick prototyping and simple production applications. If you *really* have a lot of data, you should use [Apache Mahout](https://mahout.apache.org/).

Many of the ML algorithms were taken from the great bible of [Machine Learning - Tom M. Mitchell](http://www.cs.cmu.edu/~tom/mlbook.html)

## Usage
To see an example of how to use the library, take a look at the unit tests. One or more applications are included for each one.
### Maven
```
<dependency>
    <groupId>com.github.bentorfs</groupId>
    <artifactId>ai-algorithms</artifactId>
    <version>0.2.0</version>
</dependency>
```


## Included algorithms

### Data Mining
* Agglomerative hierarchical clustering
* Association rules: apriori

### Artificial Neural Networks
* Perceptron training using perceptron rule or stochastic gradient descent
* Multi-layer feedforward networks
 * Sigmoid units 
 * Backpropagation 

### Reinforcement learning
* Q-learning, using
 * Boltzmann action selection
 * Є-greedy action selection

### Graph algorithms
* A* search

### Game playing algorithms
* Minimax
 * Alpha-beta pruning
