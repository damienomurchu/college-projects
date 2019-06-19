import java.io.FileNotFoundException;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job
{
  public void doJob() throws FileNotFoundException
  {
    Fixtures.deleteDatabase();
    Fixtures.loadModels("data.yml");
  }
}
