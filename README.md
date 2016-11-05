# Stable Architecture

The goal of this project is to show main architecture approaches in Android developing. 
Unlike [googlesamples/android-architecture], here we have provided examples with survivable business logic. It means, after view recreating, its state remains the same. 
And the important requirement for this examples is as simple and as understandable as possible.

####Implemented ways to save state:
  - Factory - singleton map that manages presenters
  - Dagger2 - manage presenter lifecycle with custom scopes
  - RetainFragment - fragment, which associated with another and affected by method setRetainInstance(true)

[googlesamples/android-architecture]: <https://github.com/googlesamples/android-architecture>
