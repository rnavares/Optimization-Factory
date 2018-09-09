# Optimization-Factory
Factory of nature inspired optimization algorithms 

Java Implementation Factory pattern based on [Nature inspired metaheuristic algorithms] (https://dl.acm.org/citation.cfm?id=1628847)
including:

* Simulated Annealing
* Bat algorithm
* Cuckoo search
* Firefly algorithm

Over a factory of classical test functions

```
=== Evaluation Rosenbrock with fmin: 0  ==

Simulated Annealing....
Evaluations: 229500
Best solution :
x1:0.03842
x2:0.0849
Best fobj = 0.006

Bat Algorithm....
Evaluations: 1000
Best solution :
x1:-0.08605
x2:0.1907
Best fobj = 0.572

Firefly....
x1: -0.27861
x2: -0.18218
Best fobj = 0.384

Cuckoo....
Evaluations: 96469
Best solution :
x1:1.00115
x2:1.00229
Best fobj = 0.0

=== Evaluation Sphere with fmin: 0 at (0,0, ..., 0) ==

Simulated Annealing....
Evaluations: 246168
Best solution :
x1:0.00884
x2:0.01127
x3:-0.04174
x4:0.03346
x5:0.0212
Best fobj = 0.004

Bat Algorithm....
Evaluations: 1000
Best solution :
x1:-3.3E-4
x2:6.2E-4
x3:0.00206
x4:-0.00148
x5:-0.00481
Best fobj = 0.0

Firefly....
x1: 0.05208
x2: -0.0663
Best fobj = 0.007

Cuckoo....
Evaluations: 21762
Best solution :
x1:-0.00253
x2:0.00181
Best fobj = 0.0

=== Evaluation Rastrigin with fmin ~ 0.0 at (0,..., 0) ==

Simulated Annealing....
Evaluations: 191250
Best solution :
x1:0.99312
x2:-0.99206
Best fobj = 1.992

Bat Algorithm....
Evaluations: 1000
Best solution :
x1:-0.00146
x2:0.99533
Best fobj = 0.995

Firefly....
x1: -0.99386
x2: -0.87557
Best fobj = 0.916

Cuckoo....
Evaluations: 3272
Best solution :
x1:-0.00102
x2:-7.5E-4
Best fobj = 0.0

```
