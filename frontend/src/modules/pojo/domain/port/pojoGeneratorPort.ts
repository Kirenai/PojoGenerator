import { Payload } from "../model/payload";

export interface PojoGeneratorRepositoryPort {
  generate: (payload: Payload) => Promise<string>;
}
