package com.vinyla_android.data.local.ext

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @author Malibin
 *
 * Created on 2020 12 24
 * Updated on 2020 12 24
 *
 * @sample
 * val foo = dataStore[KEY_FOO].first()
 *
 * 기존 아래와 같은 코드를 자주 써서, 동일한 부분을 줄이고
 * 좀 더 직관적으로 데이터를 가져오는 듯한 코드를 작성하고자 만든 확장 함수입니다.
 * val foo = dataStore.data.map { it[KEY_FOO] }.first()
 *
 */

internal operator fun <T> DataStore<Preferences>.get(key: Preferences.Key<T>): Flow<T?> {
    return this.data.map { it[key] }
}
