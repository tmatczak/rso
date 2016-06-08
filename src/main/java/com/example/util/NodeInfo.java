package com.example.util;

import com.example.types.NodeType;
import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class NodeInfo {

    public static final String NODE_ID = "id";
    public static final String NODE_ADDRESS = "ip";
    public static final String NODE_TYPE = "type";

    private  int nodeId;
    private  String nodeIPAddress;
    private NodeType nodeType;
}
