# Log Parser

A simple parser for Quake III logs.

## Built with

- Java (1.8.0_181)

## How to get it running

- Clone the repository
  `git clone https://github.com/gwyddie/log-parser`

And, to avoid complications, open it on your favorite IDE and run the program with `net.gwyddie.logparser.Program`
  as the main class.
 
<del>Haha... it's sad to admit it, but I did not find an organized way to compile all the classes, and them execute them... :(</del>

## How it works

After getting the log file, the program reads it line by line identifying, with a RegExp, useful tokens, such as,
  mainly, `InitGame` and `Kill`. Then, if the token is a `Kill`, it uses another RegExp to extract useful informations,
  such as: who killed, who died and how they get that way.

For each `InitGame` token, it sets a new game, and each `Kill` computes a new killing, following the pattern described
  on the task:
  
- Every kill adds to the total of kills
- Every kill is added to the ranking of kills by mean
- Kills by the `<world>` reduces 1 kill from the dead player
- Kills by the `<world>` are not computed as a player,
  so it should not appear on the list of players or the ranking of kills

Last, the program formats the info gathered into a JSON alike way (similar to the way given on the Gist),
  and saves it into `reports/`.

## Example of use

A `games.log` is put under the root of the project, and when the program is executed, it reads, parses, evaluates, and
  last but less important, writes separate reports for each game under `reports/` folder.

The reports are in the pattern `game-n-report.txt`, where `n` is the ID of the match.

It also includes the extra task, whose reports are printed on `deaths-n-report.txt`, where `n` is the ID too.

That's all, folks!

Thanks :)
