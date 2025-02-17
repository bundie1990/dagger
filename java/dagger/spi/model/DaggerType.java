/*
 * Copyright (C) 2021 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.spi.model;

import com.google.auto.common.MoreTypes;
import com.google.auto.value.AutoValue;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.devtools.ksp.symbol.KSType;
import javax.annotation.Nullable;
import javax.lang.model.type.TypeMirror;

/** Wrapper type for a type. */
@AutoValue
public abstract class DaggerType {

  public static DaggerType fromJava(TypeMirror typeMirror) {
    return new AutoValue_DaggerType(
        MoreTypes.equivalence().wrap(Preconditions.checkNotNull(typeMirror)),
        null);
  }

  public static DaggerType fromKsp(KSType ksType) {
    return new AutoValue_DaggerType(
        null,
        Preconditions.checkNotNull(ksType));
  }

  @Nullable
  abstract Equivalence.Wrapper<TypeMirror> typeMirror();

  @Nullable
  public TypeMirror java() {
    return typeMirror().get();
  }

  @Nullable
  public abstract KSType ksp();

  @Override
  public final String toString() {
    return java().toString();
  }
}
