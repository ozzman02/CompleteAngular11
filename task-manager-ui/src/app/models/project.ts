import { ClientLocation } from "./client-location";

export class Project {

    id: string | any;
    projectName: string | any;
    dateOfStart: Date | any;
    teamSize: number | any;
    active: boolean | any;
    status: string | any;
    clientLocationId: string | any;
    clientLocation: ClientLocation | any;
    version: number | any;
    createdDate: Date | any;
    updateDate: Date | any;

    constructor() {
        this.id = null;
        this.projectName = null;
        this.dateOfStart = null;
        this.teamSize = null;
        this.active = null;
        this.status = null;
        this.clientLocationId = null;
        this.clientLocation = new ClientLocation();
        this.version = null;
        this.createdDate = null;
        this.updateDate = null;
    }

}
