/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zeppelin.server;

import javax.ws.rs.core.Response.ResponseBuilder;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.IOException;

/**
 * text response builder.
 *
 */
public class TextResponse {
  private javax.ws.rs.core.Response.Status status;
  private String body;
  private String fileName;
  
  public TextResponse(
    javax.ws.rs.core.Response.Status status, 
    String body, 
    String fileName) throws IOException {
    this.status = status;
    this.body = body;

    fileName = URLEncoder.encode(fileName, "UTF-8");
    this.fileName = URLDecoder.decode(fileName, "ISO8859_1");
  }

  public javax.ws.rs.core.Response build() {
    ResponseBuilder r = javax.ws.rs.core.Response
      .status(status)
      .header("Content-disposition", "attachment; filename=" + this.fileName)
      .entity(this.body);

    return r.build();
  }
}
