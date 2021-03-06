package org.loopring.looprwalletnetwork.models.ethplorer.eth

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.math.BigDecimal

/**
 * Created by arknw229 on 3/13/18.
 *
 * Ethplorer API
 *
 * Cumulative market data related to a given token address
 *
 * ```
 * {
 *     "tokens": # total number of tokens listed
 *     "tokensWithPrice": # total number of tokens with price data provided
 *     "cap": # total current market cap of tokens listed
 *     "capPrevious": # total previous market cap of tokens listed
 *     "volume24h": # cumulative 24 hour volume on the tokens listed
 *     "volumePrevious": # previous cumulative volume on the tokens listed
 * }
 * ```
 *
 * @author arknw229
 */
open class EthTokenHistoryTotals : RealmObject() {

    /**
     * Total number of tokens listed
     */
    var tokens: Long? = null

    /**
     * Total number of tokens with price data provided
     */
    var tokensWithPrice: Int? = null

    /**
     * Total current market cap of tokens listed
     */
    var cap: BigDecimal?
        get() {
            return mCap?.let { BigDecimal(it) }
        }
        set(value) {
            mCap = value?.toPlainString()
        }

    @SerializedName("cap")
    var mCap: String? = null

    /**
     * Total previous market cap of tokens listed
     */
    var capPrevious: BigDecimal?
        get() {
            return mCapPrevious?.let { BigDecimal(it) }
        }
        set(value) {
            mCapPrevious = value?.toPlainString()
        }

    @SerializedName("capPrevious")
    var mCapPrevious: String? = null

    /**
     * Cumulative 24 hour volume on the tokens listed
     */
    var volume24h: BigDecimal?
        get() {
            return mVolume24h?.let { BigDecimal(it) }
        }
        set(value) {
            mVolume24h = value?.toPlainString()
        }

    @SerializedName("volume24h")
    var mVolume24h: String? = null

    /**
     * Previous cumulative volume on the tokens listed
     */
    var volumePrevious: BigDecimal?
        get() {
            return mVolumePrevious?.let { BigDecimal(it) }
        }
        set(value) {
            mVolumePrevious = value?.toPlainString()
        }

    @SerializedName("volumePrevious")
    var mVolumePrevious: String? = null

}
