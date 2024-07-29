package org.ImtiazSuperMarket.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
private Integer id;
private String username;
private String password;
private String empType;
}
