# loopr-wallet-android-network
This repository is used as a library for loopr-wallet-android, the official Loopring Wallet for 
Android.

It contains the necessary APIs and libraries to interact with all of the network-based features for 
the application. Developers are encouraged to add features, make changes, and use this library as a 
dependency for other Android Loopr Wallets.

This library uses Kotlin's Coroutines to make asynchronous requests. For more information on 
Kotlin's Coroutines, see [here](https://kotlinlang.org/docs/reference/coroutines.html).

### Adding the Library

To add this library as a dependency to your project via Gradle, add the following:

```
compile 'com.loopring.android:loopr-wallet-network:0.1.0'
```

### Using the ETH Wrapper

Sending ETH is really simple. It only requires the following:

```kotlin
fun sendMyEther(
        amount: BigDecimal, 
        destAddress: String, 
        web3j: Web3j, 
        credentials: Credentials
) = runBlocking {
    sendEth(amount, destAddress, web3j, credentials).await()
}
```

### Using the ERC-20 Token Wrapper

Sending an ERC-20 token is also easy, with the help of the wrapper class.

```kotlin
fun sendMyToken(
        contractAddress: String,
        web3j: Web3j,
        credentials: Credentials,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
        binary: String,
        receiverAddress: String,
        tokenAmount: BigInteger
) = runBlocking {
    Erc20Wrapper.createWrapper(contractAddress, web3j, credentials, gasPrice, gasLimit, binary)
            .transfer(receiverAddress, tokenAmount)
            .await()
}

```

### Setting up the Etherscan and Ethplorer APIs

To use the Etherscan API, you need to set the API key. To do so, set the `apiKey` variable, as seen
below.

Notice that the `Ethplorer` API key is optional as of right now, since they have a free tier. 

```kotlin
fun setupKey() {
    EtherscanService.apiKey = "your-api-key"
    EthplorerService.apiKey = "your-api-key" // Optional, since you can use a free key
}
```

### Using the Etherscan and Ethplorer APIs

Using the API is as simple as using [Retrofit](http://square.github.io/retrofit/), which has been 
orchestrated work to with the respective APIs.

```kotlin
// Assume that [apiKey] was already set!!
fun getAddressHistory(address: String) = runBlocking {
    EthplorerService.getService().getAddressHistory(address).await()
}
```
```kotlin
 // Assume that [apiKey] was already set!!
fun getTransactions(address: String) = runBlocking {
    EtherscanService.getService().getTransactions(address).await()
}
```

### Thanks and Credits
- [Adam Knuckey](https://github.com/aknuck) of *New Market Elements* for creating this library
- The [Loopring Foundation](https://loopring.org) and everyone working on its ecosystem
- The team working on the [Web3j](https://github.com/web3j/web3j) library, and advancing its 
capabilities 
- The [Ethereum Foundation](https://www.ethereum.org/) and everyone working on its ecosystem 