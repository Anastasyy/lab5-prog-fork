package com.megateam.lab.common.command.util;

import com.megateam.lab.common.command.Command;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder(setterPrefix = "set")
@Getter
@Setter
public class Exchange {
  @NonNull private Command command;
  @NonNull private List<String> args;
}
