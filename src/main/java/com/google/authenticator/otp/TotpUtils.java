package com.google.authenticator.otp;

/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

public class TotpUtils {

  public static String getCode(String secret) throws GeneralSecurityException,
      Base32String.DecodingException {
    final byte[] keyBytes = Base32String.decode(secret);
    Mac mac = Mac.getInstance("HMACSHA1");
    mac.init(new SecretKeySpec(keyBytes, ""));
    PasscodeGenerator pcg = new PasscodeGenerator(mac);

    long time = System.currentTimeMillis() / 1000;

    // 30 second
    TotpCounter tc = new TotpCounter(30);
    return pcg.generateResponseCode(tc.getValueAtTime(time));
  }
}
